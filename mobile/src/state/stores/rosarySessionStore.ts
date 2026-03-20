import { create } from "zustand";
import AsyncStorage from "@react-native-async-storage/async-storage";
import { createJSONStorage, persist } from "zustand/middleware";

import { getRosaryIdForWeekday } from "@/utils/rosary";

interface RosarySessionState {
  selectedRosaryId: string;
  currentStepIndex: number;
  setSelectedRosaryId: (id: string) => void;
  openRosary: (id: string) => void;
  setCurrentStepIndex: (next: number) => void;
}

export const useRosarySessionStore = create<RosarySessionState>()(
  persist(
    (set) => ({
      selectedRosaryId: getRosaryIdForWeekday(),
      currentStepIndex: 0,
      setSelectedRosaryId: (selectedRosaryId: string) => set({ selectedRosaryId }),
      openRosary: (selectedRosaryId: string) =>
        set({ selectedRosaryId, currentStepIndex: 0 }),
      setCurrentStepIndex: (currentStepIndex: number) => set({ currentStepIndex }),
    }),
    {
      name: "rosary-session",
      storage: createJSONStorage(() => AsyncStorage),
      partialize: (state) => ({
        selectedRosaryId: state.selectedRosaryId,
        currentStepIndex: state.currentStepIndex,
      }),
    },
  ),
);
