import gloriousMysteries from "@/data/rosaries/glorious_mysteries.json";
import joyfulMysteries from "@/data/rosaries/joyful_mysteries.json";
import luminousMysteries from "@/data/rosaries/luminous_mysteries.json";
import sorrowfulMysteries from "@/data/rosaries/sorrowful_mysteries.json";

import { RosaryDefinition } from "@/types/rosary";

const rosaryCollections = [
  gloriousMysteries,
  joyfulMysteries,
  luminousMysteries,
  sorrowfulMysteries,
] as RosaryDefinition[][];

export const rosaryList = rosaryCollections.flat();

export const rosariesById = rosaryList.reduce<Record<string, RosaryDefinition>>(
  (acc, rosary) => {
    acc[rosary.id] = rosary;
    return acc;
  },
  {},
);
