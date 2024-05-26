import type { NextApiRequest, NextApiResponse } from 'next'
import { Group, Rosary } from "@/data/structure/Rosary"
import { prayerDataSource } from '@/data/source/PrayerDataSource';
import { rosaryDataSource } from '@/data/source/RosaryDataSource';
import { Prayer } from '@/data/structure/Prayer';

type RosaryResponse = {
    id: string
    title: string
    subtitle: string
    groups: Group[]
    prayers: Prayer[]
}

type Query = {
    id: string
}

export default function handler(req: NextApiRequest, res: NextApiResponse<RosaryResponse | Error>) {
    const { id } = req.query as Query

    try {
        const rosary: Rosary = rosaryDataSource.getSingleRosary(id)

        const response: RosaryResponse = {
            id: rosary.id,
            title: rosary.title,
            subtitle: rosary.subtitle,
            groups: rosary.groups,
            prayers: prayerDataSource.getPrayersForRosary(rosary)
        }

        res.status(200).json(response)
    } catch (exception) {
        res.status(401).json(exception as Error)
    }
}