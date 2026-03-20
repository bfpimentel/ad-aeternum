import { create } from "zustand";

import { prayerList } from "@/data/prayers";
import { PrayerDefinition } from "@/types/rosary";

interface PrayerCatalogState {
  prayers: PrayerDefinition[];
  prayersByType: Record<string, PrayerDefinition>;
  getPrayerByType: (type?: string) => PrayerDefinition | null;
}

const prayersByType = prayerList.reduce<Record<string, PrayerDefinition>>((acc, prayer) => {
  acc[prayer.type] = prayer;
  return acc;
}, {});

export const usePrayerCatalogStore = create<PrayerCatalogState>()(() => ({
  prayers: prayerList,
  prayersByType,
  getPrayerByType: (type?: string) => {
    if (!type) return null;
    return prayersByType[type] ?? null;
  },
}));
