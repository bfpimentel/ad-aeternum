import { createContext, PropsWithChildren, useContext, useMemo, useState } from "react";
import { rosariesById, rosaryList } from "@/data/rosaries";
import { prayersByType } from "@/data/prayers";
import { ExpandedRosaryStep, RosaryDefinition } from "@/types/rosary";
import { expandRosarySteps, getRosaryIdForWeekday } from "@/utils/rosary";

interface RosarySessionValue {
  rosary: RosaryDefinition;
  allRosaries: RosaryDefinition[];
  selectedRosaryId: string;
  setSelectedRosaryId: (id: string) => void;
  openRosary: (id: string) => void;
  steps: ExpandedRosaryStep[];
  currentStepIndex: number;
  setCurrentStepIndex: (next: number) => void;
  currentStep: ExpandedRosaryStep | undefined;
  currentPrayer: ReturnType<typeof getCurrentPrayer>;
  nextStep: () => void;
  previousStep: () => void;
  progress: number;
}

function getCurrentPrayer(type?: string) {
  if (!type) return null;
  return prayersByType[type] ?? null;
}

const RosarySessionContext = createContext<RosarySessionValue | null>(null);

export function RosarySessionProvider({ children }: PropsWithChildren) {
  const [selectedRosaryId, setSelectedRosaryId] = useState<string>(
    getRosaryIdForWeekday(),
  );
  const [currentStepIndex, setCurrentStepIndexState] = useState(0);

  const rosary = rosariesById[selectedRosaryId] ?? rosaryList[0];
  const steps = useMemo(() => expandRosarySteps(rosary), [rosary]);

  const currentStepIndexSafe = Math.min(
    Math.max(currentStepIndex, 0),
    Math.max(steps.length - 1, 0),
  );
  const currentStep = steps[currentStepIndexSafe];
  const currentPrayer = getCurrentPrayer(currentStep?.type);

  const progress = steps.length === 0 ? 0 : (currentStepIndexSafe + 1) / steps.length;

  const setCurrentStepIndex = (next: number) => {
    setCurrentStepIndexState(Math.min(Math.max(next, 0), Math.max(steps.length - 1, 0)));
  };

  const nextStep = () => setCurrentStepIndex(currentStepIndexSafe + 1);
  const previousStep = () => setCurrentStepIndex(currentStepIndexSafe - 1);

  const openRosary = (id: string) => {
    setSelectedRosaryId(id);
    setCurrentStepIndexState(0);
  };

  return (
    <RosarySessionContext.Provider
      value={{
        rosary,
        allRosaries: rosaryList,
        selectedRosaryId,
        setSelectedRosaryId,
        openRosary,
        steps,
        currentStepIndex: currentStepIndexSafe,
        setCurrentStepIndex,
        currentStep,
        currentPrayer,
        nextStep,
        previousStep,
        progress,
      }}
    >
      {children}
    </RosarySessionContext.Provider>
  );
}

export function useRosarySession() {
  const context = useContext(RosarySessionContext);
  if (!context) {
    throw new Error("useRosarySession must be used inside RosarySessionProvider");
  }
  return context;
}
