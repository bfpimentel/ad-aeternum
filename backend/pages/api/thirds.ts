import type { NextApiRequest, NextApiResponse } from 'next'
import { Group, Third } from "@/data/structure/Third"
import { JoyfulMysteriesThird_PT } from "@/data/thirds/JoyfulMysteriesThird";
import { PrayerDataSource } from '@/data/source/PrayerDataSource';
import { Prayer } from '@/data/structure/Prayer';

const dataSource: PrayerDataSource = new PrayerDataSource()

type ThirdResponse = {
    id: string
    title: string
    subtitle: string
    groups: Group[]
    prayers: Prayer[]
}

export default function handler(req: NextApiRequest, res: NextApiResponse<ThirdResponse[]>) {
    const prayers: Prayer[] = dataSource.getPrayers()
    const thirds: Third[] = [new JoyfulMysteriesThird_PT()]

    const response: ThirdResponse[] = thirds.map((third) => {
        return {
            id: third.id,
            title: third.title,
            subtitle: third.subtitle,
            groups: third.groups,
            prayers: mapPrayers(third.groups, prayers)
        }
    })

    res.status(200).json(response)
}

function mapPrayers(groups: Group[], prayers: Prayer[]): Prayer[] {
    var prayerTypes: string[] = []

    groups.flatMap(({steps}) => steps).forEach(({type}) => {
        if (prayerTypes.includes(type)) return
        prayerTypes = prayerTypes.concat(type)
    })

    return prayerTypes.map(prayerType => prayers.find((prayer) => prayer.type == prayerType) as Prayer)
}