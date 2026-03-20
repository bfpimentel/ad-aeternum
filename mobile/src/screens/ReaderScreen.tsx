import { StyleSheet, Text, View } from "react-native";
import { PrimaryButton } from "@/components/PrimaryButton";
import { Screen } from "@/components/Screen";
import { useRosarySession } from "@/state/RosarySessionContext";
import { colors, radius, spacing, typography } from "@/theme/tokens";

function fallbackPrayerTitle(type: string) {
  return type.replace(/_/g, " ").replace(/\b\w/g, (char) => char.toUpperCase());
}

export function ReaderScreen() {
  const {
    rosary,
    steps,
    currentStep,
    currentStepIndex,
    currentPrayer,
    previousStep,
    nextStep,
    setCurrentStepIndex,
  } = useRosarySession();

  const isFirst = currentStepIndex === 0;
  const isLast = currentStepIndex === Math.max(steps.length - 1, 0);

  return (
    <Screen>
      <View style={styles.header}>
        <Text style={styles.title}>{rosary.title}</Text>
        <Text style={styles.subtitle}>{rosary.subtitle}</Text>
      </View>

      <View style={styles.card}>
        <Text style={styles.label}>ORAÇÃO ATUAL</Text>
        <Text style={styles.prayerTitle}>
          {currentPrayer?.title ?? fallbackPrayerTitle(currentStep?.type ?? "")}
        </Text>

        {(currentPrayer?.paragraphs ?? []).map((paragraph, index) => (
          <Text
            key={`${currentPrayer?.type ?? "step"}-${index}`}
            style={styles.paragraph}
          >
            {paragraph}
          </Text>
        ))}

        {!currentPrayer && (
          <Text style={styles.paragraph}>
            Esta etapa não possui conteúdo textual detalhado.
          </Text>
        )}

        <Text style={styles.progressText}>
          Passo {currentStepIndex + 1} de {steps.length}
        </Text>
      </View>

      <View style={styles.actionsRow}>
        <PrimaryButton
          onPress={previousStep}
          variant="secondary"
          disabled={isFirst}
          style={styles.actionButton}
        >
          Anterior
        </PrimaryButton>
        <PrimaryButton onPress={nextStep} disabled={isLast} style={styles.actionButton}>
          Próximo
        </PrimaryButton>
      </View>

      <PrimaryButton onPress={() => setCurrentStepIndex(0)} variant="secondary">
        Recomeçar Terço
      </PrimaryButton>
    </Screen>
  );
}

const styles = StyleSheet.create({
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
  card: {
    backgroundColor: colors.surface,
    borderRadius: radius.lg,
    borderWidth: 1,
    borderColor: colors.border,
    padding: spacing.lg,
    gap: spacing.sm,
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
  paragraph: {
    fontFamily: typography.sans,
    fontSize: 16,
    lineHeight: 24,
    color: colors.text,
  },
  progressText: {
    fontFamily: typography.sansMedium,
    fontSize: 12,
    color: colors.textMuted,
    marginTop: spacing.xs,
  },
  actionsRow: {
    flexDirection: "row",
    gap: spacing.sm,
  },
  actionButton: {
    flex: 1,
  },
});
