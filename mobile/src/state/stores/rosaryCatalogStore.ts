import { create } from "zustand";

import { rosaryList } from "@/data/rosaries";
import { RosaryDefinition } from "@/types/rosary";
import { getRosaryIdForWeekday } from "@/utils/rosary";

interface RosaryCatalogState {
  rosaries: RosaryDefinition[];
  rosariesById: Record<string, RosaryDefinition>;
  getRosaryById: (id: string) => RosaryDefinition | null;
  getRecommendedRosary: (date?: Date) => RosaryDefinition;
}

const rosariesById = rosaryList.reduce<Record<string, RosaryDefinition>>((acc, rosary) => {
  acc[rosary.id] = rosary;
  return acc;
}, {});

export const useRosaryCatalogStore = create<RosaryCatalogState>()(() => ({
  rosaries: rosaryList,
  rosariesById,
  getRosaryById: (id: string) => rosariesById[id] ?? null,
  getRecommendedRosary: (date = new Date()) => {
    const recommendedId = getRosaryIdForWeekday(date);
    return rosariesById[recommendedId] ?? rosaryList[0];
  },
}));
