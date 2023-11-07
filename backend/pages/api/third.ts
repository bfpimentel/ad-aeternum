import type { NextApiRequest, NextApiResponse } from 'next'
import {ThirdResponse} from "@/data/ThirdResponse";

export default function handler(
    req: NextApiRequest,
    res: NextApiResponse<ThirdResponse>
) {
    res.status(200).json(third)
}

const third: ThirdResponse = {
    groups: [
        { steps:
            [
                { type: "creed", count: 1 },
            ]
        }
    ],
    prayers: [
        { type: "creed", title: "Credo", text: "Creio em Deus Pai..." }
    ]
}