export type PrayerType = string;

export interface PrayerDefinition {
  type: PrayerType;
  title: string;
  subtitle: string;
  language: string;
  paragraphs: string[];
}

export interface RosaryStep {
  type: PrayerType;
  count: number;
}

export interface RosaryGroup {
  steps: RosaryStep[];
}

export interface RosaryDefinition {
  id: string;
  title: string;
  subtitle: string;
  language: string;
  groups: RosaryGroup[];
}

export interface ExpandedRosaryStep {
  type: PrayerType;
  groupIndex: number;
  stepIndex: number;
  repeatIndex: number;
}
