import fs from 'fs'
import path from 'path'
import { Third } from "@/data/structure/Third";

class ThirdDataSource {
    private thirds: Third[] = []

    getThirds(): Third[] {
        if (this.thirds.length != 0) return this.thirds

        const dir = path.resolve('./public', "thirds");

        const filenames = fs.readdirSync(dir);

        this.thirds = filenames
            .map(name => path.join(dir, name))
            .map((filePath) => fs.readFileSync(filePath, 'utf-8'))
            .map((json) => JSON.parse(json) as Third[])
            .flatMap((thirds) => thirds)

        return this.thirds
    }

    getSingleThird(id: string): Third {
        if (this.thirds.length == 0) {
            this.thirds = this.getThirds()
        }

        const third = this.thirds.find((third) => third.id == id)

        if (third) {
            return third
        } else {
            throw new Error(`Third with id ${id} couldn't be found.`)
        }
    }
}

export const thirdDataSource: ThirdDataSource = new ThirdDataSource()
