import { useMemo } from "react";
import { Pressable, StyleSheet, Text, View } from "react-native";
import { BottomTabNavigationProp } from "@react-navigation/bottom-tabs";
import { useNavigation } from "@react-navigation/native";
import { PrimaryButton } from "@/components/PrimaryButton";
import { Screen } from "@/components/Screen";
import { RootTabParamList } from "@/navigation/types";
import { useRosarySession } from "@/state/RosarySessionContext";
import { colors, radius, spacing, typography } from "@/theme/tokens";
import { getRosaryIdForWeekday } from "@/utils/rosary";

export function HomeScreen() {
  const navigation = useNavigation<BottomTabNavigationProp<RootTabParamList, "Home">>();
  const { allRosaries, openRosary, selectedRosaryId, progress } = useRosarySession();

  const todayRosaryId = getRosaryIdForWeekday();
  const todayRosary =
    allRosaries.find((item) => item.id === todayRosaryId) ?? allRosaries[0];
  const effectiveProgress = selectedRosaryId === todayRosary.id ? progress : 0;

  const dayLabel = useMemo(() => {
    const weekday = new Intl.DateTimeFormat("pt-BR", { weekday: "long" }).format(
      new Date(),
    );
    return weekday.charAt(0).toUpperCase() + weekday.slice(1);
  }, []);

  const openReaderWithRosary = (rosaryId: string) => {
    openRosary(rosaryId);
    navigation.navigate("Rosary", { screen: "Reader" });
  };

  return (
    <Screen>
      <View style={styles.header}>
        <Text style={styles.greeting}>A paz esteja convosco</Text>
        <Text style={styles.brand}>AD AETERNUM</Text>
        <Text style={styles.dateText}>
          {dayLabel} · {todayRosary.title}
        </Text>
      </View>

      <View style={styles.heroCard}>
        <View style={styles.heroTopRow}>
          <Text style={styles.heroLabel}>TERÇO DE HOJE</Text>
          <View style={styles.heroChip}>
            <Text style={styles.heroChipText}>{todayRosary.subtitle.toUpperCase()}</Text>
          </View>
        </View>

        <Text style={styles.heroTitle}>{todayRosary.title}</Text>

        <View style={styles.progressTrack}>
          <View
            style={[
              styles.progressFill,
              { width: `${Math.max(6, effectiveProgress * 100)}%` },
            ]}
          />
        </View>

        <PrimaryButton
          onPress={() => openReaderWithRosary(todayRosary.id)}
          variant="secondary"
          style={styles.heroButton}
        >
          Continuar Terço
        </PrimaryButton>
      </View>

      <View style={styles.section}>
        <Text style={styles.sectionTitle}>Início Rápido</Text>
        <View style={styles.chipRow}>
          {allRosaries.map((rosary) => {
            const active = rosary.id === selectedRosaryId;
            return (
              <Pressable
                key={rosary.id}
                onPress={() => openReaderWithRosary(rosary.id)}
                style={[styles.chip, active && styles.chipActive]}
              >
                <Text style={[styles.chipText, active && styles.chipTextActive]}>
                  {rosary.title.replace("Mistérios ", "")}
                </Text>
              </Pressable>
            );
          })}
        </View>
      </View>

      <View style={styles.intentionsCard}>
        <Text style={styles.intentionsTitle}>Intenções de hoje</Text>
        <Text style={styles.intentionsItem}>- Pelas famílias e pelos matrimônios</Text>
        <Text style={styles.intentionsItem}>- Pela paz no mundo</Text>
        <Text style={styles.intentionsItem}>- Por quem pede suas orações</Text>
      </View>
    </Screen>
  );
}

const styles = StyleSheet.create({
  header: {
    gap: 4,
  },
  greeting: {
    fontFamily: typography.sans,
    fontSize: 13,
    color: colors.textMuted,
  },
  brand: {
    fontFamily: typography.serif,
    fontSize: 30,
    color: colors.primaryDeep,
  },
  dateText: {
    fontFamily: typography.sansMedium,
    fontSize: 14,
    color: colors.primary,
  },
  heroCard: {
    backgroundColor: colors.primary,
    borderRadius: 24,
    padding: spacing.lg,
    gap: spacing.md,
  },
  heroTopRow: {
    flexDirection: "row",
    alignItems: "center",
    justifyContent: "space-between",
  },
  heroLabel: {
    fontFamily: typography.sansBold,
    fontSize: 11,
    color: "#FFFFFFCC",
    letterSpacing: 0.8,
  },
  heroChip: {
    backgroundColor: "#FFFFFF20",
    borderRadius: radius.pill,
    paddingVertical: 4,
    paddingHorizontal: 10,
  },
  heroChipText: {
    fontFamily: typography.sansBold,
    fontSize: 10,
    color: colors.white,
    letterSpacing: 0.5,
  },
  heroTitle: {
    fontFamily: typography.serif,
    fontSize: 28,
    color: colors.white,
  },
  progressTrack: {
    height: 8,
    borderRadius: radius.pill,
    backgroundColor: "#FFFFFF40",
    overflow: "hidden",
  },
  progressFill: {
    height: "100%",
    borderRadius: radius.pill,
    backgroundColor: colors.gold,
  },
  heroButton: {
    backgroundColor: colors.white,
    borderColor: "transparent",
  },
  section: {
    gap: spacing.sm,
  },
  sectionTitle: {
    fontFamily: typography.serif,
    fontSize: 24,
    color: colors.primaryDeep,
  },
  chipRow: {
    flexDirection: "row",
    flexWrap: "wrap",
    gap: spacing.sm,
  },
  chip: {
    backgroundColor: colors.surface,
    borderRadius: radius.md,
    paddingVertical: 10,
    paddingHorizontal: 14,
    borderWidth: 1,
    borderColor: colors.border,
  },
  chipActive: {
    backgroundColor: colors.primarySoft,
    borderColor: colors.primary,
  },
  chipText: {
    fontFamily: typography.sansMedium,
    fontSize: 13,
    color: colors.text,
  },
  chipTextActive: {
    color: colors.primaryDeep,
    fontFamily: typography.sansBold,
  },
  intentionsCard: {
    backgroundColor: colors.surface,
    borderRadius: radius.lg,
    borderWidth: 1,
    borderColor: colors.border,
    padding: spacing.lg,
    gap: 8,
  },
  intentionsTitle: {
    fontFamily: typography.sansBold,
    fontSize: 16,
    color: colors.primaryDeep,
  },
  intentionsItem: {
    fontFamily: typography.sans,
    fontSize: 14,
    color: colors.text,
  },
});
