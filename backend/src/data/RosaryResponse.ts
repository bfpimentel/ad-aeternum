export type RosaryResponse = {
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