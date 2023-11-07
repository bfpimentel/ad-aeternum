import type { NextApiRequest, NextApiResponse } from 'next'
import { Third } from "@/data/thirds/Third";
import { Constants } from '@/data/Constants';
import { Creed_PT } from '@/data/prayers/Creed';

export default function handler(req: NextApiRequest, res: NextApiResponse<[Third]>) {
    res.status(200).json([third])
}

const third: Third = {
    id: "mary",
    title: "Terço Mariano",
    groups: [
        {
            steps: [
                { type: Constants.TYPE_CREED, count: 1 },
            ]
        }
    ],
    prayers: [
        new Creed_PT(),
    ]
}