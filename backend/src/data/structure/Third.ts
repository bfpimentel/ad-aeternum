export interface Third {
    id: string
    title: string
    subtitle: string
    language: string
    groups: Group[]
}

export type Group = {
    steps: Step[]
}

export type Step = {
    type: string
    count: number
}