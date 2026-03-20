import { ExpandedRosaryStep, RosaryDefinition } from "@/types/rosary";

export function getRosaryIdForWeekday(date = new Date()): string {
  const day = date.getDay();
  if (day === 1 || day === 6) return "joyful_mysteries";
  if (day === 2 || day === 5) return "sorrowful_mysteries";
  if (day === 3 || day === 0) return "glorious_mysteries";
  return "luminous_mysteries";
}

export function expandRosarySteps(rosary: RosaryDefinition): ExpandedRosaryStep[] {
  const expanded: ExpandedRosaryStep[] = [];

  rosary.groups.forEach((group, groupIndex) => {
    group.steps.forEach((step, stepIndex) => {
      for (let repeatIndex = 0; repeatIndex < step.count; repeatIndex += 1) {
        expanded.push({
          type: step.type,
          groupIndex,
          stepIndex,
          repeatIndex,
        });
      }
    });
  });

  return expanded;
}
