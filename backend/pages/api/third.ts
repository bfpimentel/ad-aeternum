import type { NextApiRequest, NextApiResponse } from 'next'
import { Group, Third } from "@/data/structure/Third"
import { prayerDataSource } from '@/data/source/PrayerDataSource';
import { thirdDataSource } from '@/data/source/ThirdDataSource';
import { Prayer } from '@/data/structure/Prayer';

type ThirdResponse = {
    id: string
    title: string
    subtitle: string
    groups: Group[]
    prayers: Prayer[]
}

type Query = {
    id: string
}

export default function handler(req: NextApiRequest, res: NextApiResponse<ThirdResponse | Error>) {
    const { id } = req.query as Query

    try {
        const third: Third = thirdDataSource.getSingleThird(id)

        const response: ThirdResponse = {
            id: third.id,
            title: third.title,
            subtitle: third.subtitle,
            groups: third.groups,
            prayers: prayerDataSource.getPrayersForThird(third)
        }

        res.status(200).json(response)
    } catch (exception) {
        res.status(401).json(exception as Error)
    }
}