import { Third } from "@/data/structure/Third";
import { JoyfulMysteriesThird_PT } from "../thirds/JoyfulMysteriesThird";

class ThirdDataSource {
    private thirds: Third[] = []

    getThirds(): Third[] {
        if (this.thirds.length != 0) return this.thirds

        this.thirds = [
            new JoyfulMysteriesThird_PT()
        ]

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
