import { Prayer } from "@/data/prayers/Prayer"

export interface Third {
    id: string
    title: string
    groups: Group[]
    prayers: Prayer[]
}

type Group = {
    steps: Step[]
}

type Step = {
    type: string
    count: number
}