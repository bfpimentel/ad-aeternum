import { useMemo, useState } from "react";
import { Pressable, StyleSheet, Text, TextInput, View } from "react-native";
import { useNavigation } from "@react-navigation/native";
import { NativeStackNavigationProp } from "@react-navigation/native-stack";
import { Feather } from "@expo/vector-icons";
import { Screen } from "@/components/Screen";
import { RosaryStackParamList } from "@/navigation/types";
import { useRosarySession } from "@/state/RosarySessionContext";
import { colors, radius, spacing, typography } from "@/theme/tokens";

export function LibraryScreen() {
  const navigation = useNavigation<NativeStackNavigationProp<RosaryStackParamList>>();
  const { allRosaries, selectedRosaryId, openRosary } = useRosarySession();
  const [query, setQuery] = useState("");

  const filtered = useMemo(() => {
    const q = query.trim().toLowerCase();
    if (!q) return allRosaries;
    return allRosaries.filter((rosary) => {
      return (
        rosary.title.toLowerCase().includes(q) ||
        rosary.subtitle.toLowerCase().includes(q)
      );
    });
  }, [allRosaries, query]);

  const openReader = (rosaryId: string) => {
    openRosary(rosaryId);
    navigation.navigate("Reader");
  };

  return (
    <Screen>
      <View style={styles.header}>
        <Text style={styles.title}>Biblioteca</Text>
        <Text style={styles.subtitle}>Escolha um mistério e comece sua oração</Text>
      </View>

      <View style={styles.searchBox}>
        <Feather name="search" size={16} color={colors.textMuted} />
        <TextInput
          value={query}
          onChangeText={setQuery}
          placeholder="Buscar mistérios ou orações"
          placeholderTextColor={colors.textMuted}
          style={styles.searchInput}
        />
      </View>

      <View style={styles.list}>
        {filtered.map((rosary) => {
          const isActive = rosary.id === selectedRosaryId;
          return (
            <Pressable
              key={rosary.id}
              onPress={() => openReader(rosary.id)}
              style={[styles.card, isActive && styles.cardActive]}
            >
              <View style={styles.cardBody}>
                <Text style={styles.cardTitle}>{rosary.title}</Text>
                <Text style={styles.cardSubtitle}>{rosary.subtitle}</Text>
              </View>
              <View style={styles.cardButton}>
                <Text style={styles.cardButtonText}>ABRIR</Text>
              </View>
            </Pressable>
          );
        })}
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
  searchBox: {
    flexDirection: "row",
    alignItems: "center",
    gap: 8,
    backgroundColor: colors.surface,
    borderRadius: radius.md,
    borderWidth: 1,
    borderColor: colors.border,
    paddingHorizontal: spacing.md,
    paddingVertical: 10,
  },
  searchInput: {
    flex: 1,
    fontFamily: typography.sans,
    fontSize: 14,
    color: colors.text,
  },
  list: {
    gap: spacing.sm,
  },
  card: {
    flexDirection: "row",
    alignItems: "center",
    gap: spacing.sm,
    backgroundColor: colors.surface,
    borderRadius: 18,
    borderWidth: 1,
    borderColor: colors.border,
    padding: spacing.md,
  },
  cardActive: {
    backgroundColor: colors.primarySoft,
  },
  cardBody: {
    flex: 1,
    gap: 2,
  },
  cardTitle: {
    fontFamily: typography.serif,
    fontSize: 25,
    color: colors.primaryDeep,
  },
  cardSubtitle: {
    fontFamily: typography.sans,
    fontSize: 13,
    color: colors.textMuted,
  },
  cardButton: {
    backgroundColor: colors.primary,
    borderRadius: 12,
    paddingVertical: 8,
    paddingHorizontal: 12,
  },
  cardButtonText: {
    fontFamily: typography.sansBold,
    fontSize: 11,
    letterSpacing: 0.6,
    color: colors.white,
  },
});
