import { PropsWithChildren } from "react";
import { Pressable, StyleSheet, Text, ViewStyle } from "react-native";
import { colors, radius, spacing, typography } from "@/theme/tokens";

interface PrimaryButtonProps extends PropsWithChildren {
  onPress: () => void;
  variant?: "primary" | "secondary";
  disabled?: boolean;
  style?: ViewStyle;
}

export function PrimaryButton({
  children,
  onPress,
  variant = "primary",
  disabled = false,
  style,
}: PrimaryButtonProps) {
  const isPrimary = variant === "primary";

  return (
    <Pressable
      onPress={onPress}
      disabled={disabled}
      style={({ pressed }) => [
        styles.button,
        isPrimary ? styles.primary : styles.secondary,
        pressed && !disabled && styles.pressed,
        disabled && styles.disabled,
        style,
      ]}
    >
      <Text style={[styles.text, isPrimary ? styles.textPrimary : styles.textSecondary]}>
        {children}
      </Text>
    </Pressable>
  );
}

const styles = StyleSheet.create({
  button: {
    alignItems: "center",
    justifyContent: "center",
    borderRadius: radius.md,
    paddingVertical: spacing.md,
    paddingHorizontal: spacing.lg,
  },
  primary: {
    backgroundColor: colors.primary,
  },
  secondary: {
    backgroundColor: colors.surface,
    borderWidth: 1,
    borderColor: colors.border,
  },
  text: {
    fontFamily: typography.sansBold,
    fontSize: 14,
  },
  textPrimary: {
    color: colors.white,
  },
  textSecondary: {
    color: colors.primaryDeep,
  },
  pressed: {
    opacity: 0.88,
  },
  disabled: {
    opacity: 0.5,
  },
});
