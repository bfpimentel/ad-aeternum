import fs from 'fs'
import path from 'path'
import { Rosary } from "@/data/structure/Rosary";

class RosaryDataSource {
    private rosaries: Rosary[] = []

    getRosaries(): Rosary[] {
        if (this.rosaries.length != 0) return this.rosaries

        const dir = path.resolve('./public', "rosaries");

        const filenames = fs.readdirSync(dir);

        this.rosaries = filenames
            .map(name => path.join(dir, name))
            .map((filePath) => fs.readFileSync(filePath, 'utf-8'))
            .map((json) => JSON.parse(json) as Rosary[])
            .flatMap((rosaries) => rosaries)

        return this.rosaries
    }

    getSingleRosary(id: string): Rosary {
        if (this.rosaries.length == 0) {
            this.rosaries = this.getRosaries()
        }

        const rosary = this.rosaries.find((rosary) => rosary.id == id)

        if (rosary) {
            return rosary
        } else {
            throw new Error(`Rosary with id ${id} couldn't be found.`)
        }
    }
}

export const rosaryDataSource: RosaryDataSource = new RosaryDataSource()
