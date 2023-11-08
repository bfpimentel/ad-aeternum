export interface Third {
    id: string
    title: string
    subtitle: string
    groups: Group[]
}

export type Group = {
    steps: Step[]
}

export type Step = {
    type: string
    count: number
}