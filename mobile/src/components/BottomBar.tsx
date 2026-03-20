import { Feather } from "@expo/vector-icons";
import { BottomTabBarProps } from "@react-navigation/bottom-tabs";
import { Pressable, StyleSheet, Text, View } from "react-native";
import { useSafeAreaInsets } from "react-native-safe-area-context";
import { colors, typography } from "@/theme/tokens";

const tabMeta = {
  Home: { label: "INÍCIO", icon: "home" as const },
  Rosary: { label: "TERÇO", icon: "book-open" as const },
  Settings: { label: "AJUSTES", icon: "settings" as const },
};

export function BottomBar({ state, descriptors, navigation }: BottomTabBarProps) {
  const insets = useSafeAreaInsets();

  return (
    <View style={[styles.wrapper, { paddingBottom: Math.max(16, insets.bottom) }]}>
      <View style={styles.pill}>
        {state.routes.map((route, index) => {
          const isFocused = state.index === index;
          const meta = tabMeta[route.name as keyof typeof tabMeta];

          const onPress = () => {
            const event = navigation.emit({
              type: "tabPress",
              target: route.key,
              canPreventDefault: true,
            });

            if (!isFocused && !event.defaultPrevented) {
              navigation.navigate(route.name, route.params);
            }
          };

          const onLongPress = () => {
            navigation.emit({
              type: "tabLongPress",
              target: route.key,
            });
          };

          const accessibilityLabel =
            descriptors[route.key].options.tabBarAccessibilityLabel;

          return (
            <Pressable
              key={route.key}
              onPress={onPress}
              onLongPress={onLongPress}
              accessibilityRole="button"
              accessibilityState={isFocused ? { selected: true } : {}}
              accessibilityLabel={accessibilityLabel}
              style={[styles.item, isFocused && styles.itemActive]}
            >
              <Feather
                name={meta.icon}
                size={16}
                color={isFocused ? colors.white : colors.textMuted}
              />
              <Text style={[styles.label, isFocused && styles.labelActive]}>
                {meta.label}
              </Text>
            </Pressable>
          );
        })}
      </View>
    </View>
  );
}

const styles = StyleSheet.create({
  wrapper: {
    backgroundColor: colors.background,
    paddingHorizontal: 21,
    paddingTop: 12,
  },
  pill: {
    flexDirection: "row",
    gap: 6,
    backgroundColor: colors.surface,
    borderRadius: 36,
    padding: 4,
    borderWidth: 1,
    borderColor: colors.border,
  },
  item: {
    flex: 1,
    height: 54,
    borderRadius: 26,
    alignItems: "center",
    justifyContent: "center",
    gap: 4,
  },
  itemActive: {
    backgroundColor: colors.primary,
  },
  label: {
    fontFamily: typography.sansMedium,
    fontSize: 9,
    letterSpacing: 0.5,
    color: colors.textMuted,
  },
  labelActive: {
    color: colors.white,
  },
});
