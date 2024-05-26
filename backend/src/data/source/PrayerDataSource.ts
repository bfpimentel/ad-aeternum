import fs from 'fs'
import path from 'path'
import { Prayer } from "@/data/structure/Prayer"
import { Rosary } from '@/data/structure/Rosary'

class PrayerDataSource {
    private prayers: Prayer[] = []

    private getPrayers(): Prayer[] {
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

    getPrayersForRosary(rosary: Rosary): Prayer[] {
        if (this.prayers.length == 0) {
            this.prayers = this.getPrayers()
        }

        var prayerTypes: string[] = []
    
        rosary.groups.flatMap(({steps}) => steps).forEach(({type}) => {
            if (prayerTypes.includes(type)) return
            prayerTypes = prayerTypes.concat(type)
        })
    
        return prayerTypes.map(prayerType => this.prayers.find((prayer) => prayer.type == prayerType) as Prayer)
    }
}

export const prayerDataSource: PrayerDataSource = new PrayerDataSource() 
