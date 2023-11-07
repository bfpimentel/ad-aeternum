export type ThirdResponse = {
    groups: [Group]
    prayers: [Prayer]
}

type Group = {
    steps: [Step]
}

type Step = {
    type: string
    count: number
}

type Prayer = {
    type: string
    title: string
    text: string
}