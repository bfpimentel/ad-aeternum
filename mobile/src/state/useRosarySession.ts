import { useMemo } from "react";

import { ExpandedRosaryStep, PrayerDefinition, RosaryDefinition } from "@/types/rosary";
import { expandRosarySteps } from "@/utils/rosary";

import { usePrayerCatalogStore } from "@/state/stores/prayerCatalogStore";
import { useRosaryCatalogStore } from "@/state/stores/rosaryCatalogStore";
import { useRosarySessionStore } from "@/state/stores/rosarySessionStore";

export interface RosarySessionView {
  rosary: RosaryDefinition;
  allRosaries: RosaryDefinition[];
  recommendedRosary: RosaryDefinition;
  selectedRosaryId: string;
  setSelectedRosaryId: (id: string) => void;
  openRosary: (id: string) => void;
  steps: ExpandedRosaryStep[];
  currentStepIndex: number;
  setCurrentStepIndex: (next: number) => void;
  currentStep: ExpandedRosaryStep | undefined;
  currentPrayer: PrayerDefinition | null;
  nextStep: () => void;
  previousStep: () => void;
  progress: number;
}

function clampStepIndex(index: number, maxStepIndex: number) {
  return Math.min(Math.max(index, 0), Math.max(maxStepIndex, 0));
}

function useRosaryCatalog() {
  const allRosaries = useRosaryCatalogStore((state) => state.rosaries);
  const recommendedRosary = useRosaryCatalogStore((state) =>
    state.getRecommendedRosary(),
  );

  return { allRosaries, recommendedRosary };
}

function useRosarySelection() {
  const selectedRosaryId = useRosarySessionStore((state) => state.selectedRosaryId);
  const setSelectedRosaryId = useRosarySessionStore((state) => state.setSelectedRosaryId);
  const openRosary = useRosarySessionStore((state) => state.openRosary);

  return {
    selectedRosaryId,
    setSelectedRosaryId,
    openRosary,
  };
}

function useResolvedRosary(selectedRosaryId: string, allRosaries: RosaryDefinition[]) {
  const getRosaryById = useRosaryCatalogStore((state) => state.getRosaryById);
  return getRosaryById(selectedRosaryId) ?? allRosaries[0];
}

function useRosaryReaderState(rosary: RosaryDefinition) {
  const currentStepIndex = useRosarySessionStore((state) => state.currentStepIndex);
  const setCurrentStepIndexRaw = useRosarySessionStore(
    (state) => state.setCurrentStepIndex,
  );
  const getPrayerByType = usePrayerCatalogStore((state) => state.getPrayerByType);

  const steps = useMemo(() => expandRosarySteps(rosary), [rosary]);
  const maxStepIndex = Math.max(steps.length - 1, 0);
  const currentStepIndexSafe = clampStepIndex(currentStepIndex, maxStepIndex);
  const currentStep = steps[currentStepIndexSafe];
  const currentPrayer = getPrayerByType(currentStep?.type);
  const progress = steps.length === 0 ? 0 : (currentStepIndexSafe + 1) / steps.length;

  const setCurrentStepIndex = (next: number) => {
    setCurrentStepIndexRaw(clampStepIndex(next, maxStepIndex));
  };

  const nextStep = () => setCurrentStepIndex(currentStepIndexSafe + 1);
  const previousStep = () => setCurrentStepIndex(currentStepIndexSafe - 1);

  return {
    steps,
    currentStepIndex: currentStepIndexSafe,
    setCurrentStepIndex,
    currentStep,
    currentPrayer,
    nextStep,
    previousStep,
    progress,
  };
}

export function useRosarySession(): RosarySessionView {
  const { allRosaries, recommendedRosary } = useRosaryCatalog();
  const { selectedRosaryId, setSelectedRosaryId, openRosary } = useRosarySelection();
  const rosary = useResolvedRosary(selectedRosaryId, allRosaries);
  const readerState = useRosaryReaderState(rosary);

  return {
    rosary,
    allRosaries,
    recommendedRosary,
    selectedRosaryId,
    setSelectedRosaryId,
    openRosary,
    ...readerState,
  };
}
