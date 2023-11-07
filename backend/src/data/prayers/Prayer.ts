export interface Prayer {
    type: string
    title: string
    paragraphs: string[]
}

export function trimAll(values: string[]): string[] {
    return values.map(paragraph => paragraph.replace(/(\n)\s+/g, '$1'))
}