import { StyleSheet, Text, View } from "react-native";
import { Screen } from "@/components/Screen";
import { colors, radius, spacing, typography } from "@/theme/tokens";

export function SettingsScreen() {
  return (
    <Screen>
      <View style={styles.header}>
        <Text style={styles.title}>Ajustes</Text>
        <Text style={styles.subtitle}>Preferências de oração e leitura</Text>
      </View>

      <View style={styles.card}>
        <Text style={styles.itemTitle}>Idioma</Text>
        <Text style={styles.itemValue}>Português (Brasil)</Text>
      </View>

      <View style={styles.card}>
        <Text style={styles.itemTitle}>Fonte do conteúdo</Text>
        <Text style={styles.itemValue}>
          Estruturas oficiais do Rosário carregadas em JSON
        </Text>
      </View>
    </Screen>
  );
}

const styles = StyleSheet.create({
  header: {
    gap: 4,
  },
  title: {
    fontFamily: typography.serif,
    fontSize: 34,
    color: colors.primaryDeep,
  },
  subtitle: {
    fontFamily: typography.sans,
    fontSize: 14,
    color: colors.textMuted,
  },
  card: {
    backgroundColor: colors.surface,
    borderRadius: radius.lg,
    borderWidth: 1,
    borderColor: colors.border,
    padding: spacing.lg,
    gap: 6,
  },
  itemTitle: {
    fontFamily: typography.sansBold,
    fontSize: 15,
    color: colors.primaryDeep,
  },
  itemValue: {
    fontFamily: typography.sans,
    fontSize: 14,
    color: colors.text,
  },
});
