import type { NextApiRequest, NextApiResponse } from 'next'
import { Third } from "@/data/structure/Third"
import { thirdDataSource } from '@/data/source/ThirdDataSource';

type ThirdListResponse = {
    id: string
    title: string
    subtitle: string
}

export default function handler(req: NextApiRequest, res: NextApiResponse<ThirdListResponse[]>) {
    const thirds: Third[] = thirdDataSource.getThirds()

    const response: ThirdListResponse[] = thirds.map((third) => {
        return {
            id: third.id,
            title: third.title,
            subtitle: third.subtitle,
        }
    })

    res.status(200).json(response)
}
