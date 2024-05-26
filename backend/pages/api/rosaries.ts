import type { NextApiRequest, NextApiResponse } from 'next'
import { Rosary } from "@/data/structure/Rosary"
import { rosaryDataSource } from '@/data/source/RosaryDataSource';

type RosaryItemResponse = {
    id: string
    title: string
    subtitle: string
}

export default function handler(req: NextApiRequest, res: NextApiResponse<RosaryItemResponse[]>) {
    const rosaries: Rosary[] = rosaryDataSource.getRosaries()

    const response: RosaryItemResponse[] = rosaries.map((rosary) => {
        return {
            id: rosary.id,
            title: rosary.title,
            subtitle: rosary.subtitle,
        }
    })

    res.status(200).json(response)
}
