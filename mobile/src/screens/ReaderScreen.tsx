import { useEffect, useMemo, useRef } from "react";
import {
  Pressable,
  ScrollView,
  StyleSheet,
  Text,
  useWindowDimensions,
  View,
} from "react-native";
import { Feather } from "@expo/vector-icons";
import { useNavigation } from "@react-navigation/native";
import { NativeStackNavigationProp } from "@react-navigation/native-stack";
import { Screen } from "@/components/Screen";
import { RosaryStackParamList } from "@/navigation/types";
import { usePrayerCatalogStore } from "@/state/stores/prayerCatalogStore";
import { useRosarySession } from "@/state/useRosarySession";
import { colors, radius, spacing, typography } from "@/theme/tokens";

function fallbackPrayerTitle(type: string) {
  return type.replace(/_/g, " ").replace(/\b\w/g, (char) => char.toUpperCase());
}

function splitPrayerContent(paragraphs: string[]) {
  if (paragraphs.length === 0) {
    return { content: paragraphs, reference: null as string | null };
  }

  const lastParagraph = paragraphs[paragraphs.length - 1]?.trim() ?? "";
  const match = /^ref=\((.+)\)$/i.exec(lastParagraph);

  if (!match) {
    return { content: paragraphs, reference: null as string | null };
  }

  return {
    content: paragraphs.slice(0, -1),
    reference: match[1].trim(),
  };
}

export function ReaderScreen() {
  const navigation = useNavigation<NativeStackNavigationProp<RosaryStackParamList>>();
  const { width } = useWindowDimensions();
  const pagerRef = useRef<ScrollView | null>(null);

  const { rosary, steps, currentStepIndex, previousStep, nextStep, setCurrentStepIndex } =
    useRosarySession();
  const getPrayerByType = usePrayerCatalogStore((state) => state.getPrayerByType);

  const isFirst = currentStepIndex === 0;
  const isLast = currentStepIndex === Math.max(steps.length - 1, 0);
  const pageWidth = useMemo(() => Math.max(width - spacing.md * 2, 1), [width]);

  useEffect(() => {
    pagerRef.current?.scrollTo({
      x: currentStepIndex * pageWidth,
      y: 0,
      animated: true,
    });
  }, [currentStepIndex, pageWidth]);

  const onPagerMomentumEnd = (offsetX: number) => {
    const nextIndex = Math.round(offsetX / pageWidth);
    if (nextIndex !== currentStepIndex) {
      setCurrentStepIndex(nextIndex);
    }
  };

  const goToPrevious = () => {
    if (isFirst) return;
    previousStep();
  };

  const goToNext = () => {
    if (isLast) return;
    nextStep();
  };

  return (
    <Screen scroll={false} contentStyle={styles.container}>
      <View style={styles.topRow}>
        <Pressable
          onPress={() => navigation.navigate("Library")}
          style={({ pressed }) => [styles.backButton, pressed && styles.pressed]}
        >
          <Feather name="arrow-left" size={18} color={colors.primaryDeep} />
          <Text style={styles.backButtonText}>Biblioteca</Text>
        </Pressable>
      </View>

      <View style={styles.header}>
        <Text style={styles.title}>{rosary.title}</Text>
        <Text style={styles.subtitle}>{rosary.subtitle}</Text>
      </View>

      <View style={styles.pagerContainer}>
        <ScrollView
          ref={pagerRef}
          horizontal
          pagingEnabled
          showsHorizontalScrollIndicator={false}
          onMomentumScrollEnd={(event) =>
            onPagerMomentumEnd(event.nativeEvent.contentOffset.x)
          }
        >
          {steps.map((step, stepIndex) => {
            const stepPrayer = getPrayerByType(step.type);
            const parsedPrayer = splitPrayerContent(stepPrayer?.paragraphs ?? []);

            return (
              <View key={`${step.type}-${stepIndex}`} style={[styles.page, { width: pageWidth }]}>
                <View style={styles.card}>
                  <Text style={styles.label}>ORAÇÃO ATUAL</Text>
                  <Text style={styles.prayerTitle}>
                    {stepPrayer?.title ?? fallbackPrayerTitle(step.type)}
                  </Text>
                  {parsedPrayer.reference && (
                    <Text style={styles.prayerReference}>{parsedPrayer.reference}</Text>
                  )}

                  <ScrollView
                    style={styles.cardScroll}
                    contentContainerStyle={styles.cardScrollContent}
                    showsVerticalScrollIndicator={false}
                  >
                    {parsedPrayer.content.map((paragraph, index) => (
                      <Text
                        key={`${step.type}-${stepIndex}-${index}`}
                        style={styles.paragraph}
                      >
                        {paragraph}
                      </Text>
                    ))}

                    {!stepPrayer && (
                      <Text style={styles.paragraph}>
                        Esta etapa não possui conteúdo textual detalhado.
                      </Text>
                    )}
                  </ScrollView>
                </View>
              </View>
            );
          })}
        </ScrollView>
      </View>

      <View style={styles.bottomActions}>
        <View style={styles.actionsRow}>
          <Pressable
            onPress={goToPrevious}
            disabled={isFirst}
            style={({ pressed }) => [
              styles.arrowButton,
              isFirst && styles.arrowDisabled,
              pressed && !isFirst && styles.pressed,
            ]}
          >
            <Feather name="chevron-left" size={20} color={colors.primaryDeep} />
          </Pressable>

          <Text style={styles.progressText}>
            Passo {currentStepIndex + 1} de {steps.length}
          </Text>

          <Pressable
            onPress={goToNext}
            disabled={isLast}
            style={({ pressed }) => [
              styles.arrowButton,
              isLast && styles.arrowDisabled,
              pressed && !isLast && styles.pressed,
            ]}
          >
            <Feather name="chevron-right" size={20} color={colors.primaryDeep} />
          </Pressable>
        </View>

        <Pressable
          onPress={() => setCurrentStepIndex(0)}
          style={({ pressed }) => [styles.restartButton, pressed && styles.pressed]}
        >
          <Text style={styles.restartButtonText}>Recomeçar Terço</Text>
        </Pressable>
      </View>
    </Screen>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    paddingHorizontal: spacing.md,
    paddingTop: spacing.md,
    paddingBottom: spacing.lg,
    gap: spacing.md,
  },
  topRow: {
    alignItems: "flex-start",
  },
  backButton: {
    flexDirection: "row",
    alignItems: "center",
    gap: 6,
    paddingVertical: 6,
    paddingHorizontal: 8,
    borderRadius: radius.pill,
    backgroundColor: colors.surface,
    borderWidth: 1,
    borderColor: colors.border,
  },
  backButtonText: {
    fontFamily: typography.sansMedium,
    fontSize: 13,
    color: colors.primaryDeep,
  },
  header: {
    alignItems: "center",
    gap: 2,
  },
  title: {
    fontFamily: typography.serif,
    fontSize: 30,
    color: colors.primaryDeep,
    textAlign: "center",
  },
  subtitle: {
    fontFamily: typography.sans,
    fontSize: 13,
    color: colors.textMuted,
  },
  pagerContainer: {
    flex: 1,
  },
  page: {
    flex: 1,
  },
  card: {
    flex: 1,
    backgroundColor: colors.surface,
    borderRadius: radius.lg,
    borderWidth: 1,
    borderColor: colors.border,
    padding: spacing.md,
    gap: spacing.sm,
  },
  cardScroll: {
    flex: 1,
  },
  cardScrollContent: {
    gap: spacing.sm,
    paddingBottom: spacing.xs,
  },
  label: {
    fontFamily: typography.sansBold,
    fontSize: 11,
    letterSpacing: 0.7,
    color: colors.primary,
  },
  prayerTitle: {
    fontFamily: typography.serif,
    fontSize: 30,
    color: colors.primaryDeep,
  },
  prayerReference: {
    fontFamily: typography.sansMedium,
    fontSize: 13,
    color: colors.textMuted,
  },
  paragraph: {
    fontFamily: typography.sans,
    fontSize: 16,
    lineHeight: 24,
    color: colors.text,
  },
  progressText: {
    fontFamily: typography.sansMedium,
    fontSize: 13,
    color: colors.textMuted,
    textAlign: "center",
  },
  bottomActions: {
    marginTop: "auto",
    alignItems: "center",
    gap: spacing.sm,
    paddingTop: spacing.xs,
  },
  actionsRow: {
    flexDirection: "row",
    alignItems: "center",
    justifyContent: "center",
    gap: spacing.md,
  },
  arrowButton: {
    width: 40,
    height: 40,
    borderRadius: radius.pill,
    borderWidth: 1,
    borderColor: colors.border,
    backgroundColor: colors.surface,
    alignItems: "center",
    justifyContent: "center",
  },
  arrowDisabled: {
    opacity: 0.45,
  },
  restartButton: {
    paddingVertical: 4,
    paddingHorizontal: 8,
  },
  restartButtonText: {
    fontFamily: typography.sansMedium,
    fontSize: 12,
    color: colors.primary,
  },
  pressed: {
    opacity: 0.86,
  },
});
