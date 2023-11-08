import fs from 'fs'
import path from 'path'
import { Prayer } from "@/data/structure/Prayer"

export class PrayerDataSource {
    private prayers: Prayer[] = []

    getPrayers(): Prayer[] {
        if (this.prayers.length != 0) return this.prayers

        const dir = path.resolve('./public', "prayers");

        const filenames = fs.readdirSync(dir);

        this.prayers = filenames
            .map(name => path.join(dir, name))
            .map((filePath) => fs.readFileSync(filePath, 'utf-8'))
            .map((json) => JSON.parse(json) as Prayer[])
            .flatMap((prayers) => prayers)

        return this.prayers
    }
}
