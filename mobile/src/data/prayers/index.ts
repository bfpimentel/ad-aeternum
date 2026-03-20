import creed from "@/data/prayers/creed.json";
import cross from "@/data/prayers/cross.json";
import gloriousMysteries from "@/data/prayers/glorious_mysteries.json";
import glory from "@/data/prayers/glory.json";
import hailHolyQueen from "@/data/prayers/hail_holy_queen.json";
import hailMary from "@/data/prayers/hail_mary.json";
import infiniteGrace from "@/data/prayers/infinite_grace.json";
import joyfulMysteries from "@/data/prayers/joyful_mysteries.json";
import luminousMysteries from "@/data/prayers/luminous_mysteries.json";
import offer from "@/data/prayers/offer.json";
import ohMyJesus from "@/data/prayers/oh_my_jesus.json";
import ourFather from "@/data/prayers/our_father.json";
import sorrowfulMysteries from "@/data/prayers/sorrowful_mysteries.json";

import { PrayerDefinition } from "@/types/rosary";

const prayerCollections = [
  creed,
  cross,
  gloriousMysteries,
  glory,
  hailHolyQueen,
  hailMary,
  infiniteGrace,
  joyfulMysteries,
  luminousMysteries,
  offer,
  ohMyJesus,
  ourFather,
  sorrowfulMysteries,
] as PrayerDefinition[][];

export const prayerList = prayerCollections.flat();

export const prayersByType = prayerList.reduce<Record<string, PrayerDefinition>>(
  (acc, prayer) => {
    acc[prayer.type] = prayer;
    return acc;
  },
  {},
);
