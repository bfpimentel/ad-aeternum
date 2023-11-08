import { Third, Group } from "@/data/structure/Third";
import { Constants } from "@/data/Constants";

export class JoyfulMysteriesThird_PT implements Third {
    id: string = "joyful_mysteries"
    title: string = "Mistérios Gozosos"
    subtitle: string = ""
    groups: Group[] = [
        { 
            steps: [
                { type: Constants.PRAYER_CROSS, count: 1 },
                { type: Constants.PRAYER_OFFER, count: 1 },
                { type: Constants.PRAYER_CREED, count: 1 },
                { type: Constants.PRAYER_OUR_FATHER, count: 1 },
                { type: Constants.PRAYER_HAIL_MARY, count: 3 },
                { type: Constants.PRAYER_GLORY, count: 1 },
                { type: Constants.PRAYER_OH_MY_JESUS, count: 1 },
            ]
        },
        { 
            steps: [
                { type: Constants.PRAYER_JOYFUL_MYSTERY_FIRST, count: 1 },
                { type: Constants.PRAYER_OUR_FATHER, count: 1 },
                { type: Constants.PRAYER_HAIL_MARY, count: 10 },
                { type: Constants.PRAYER_GLORY, count: 1 },
                { type: Constants.PRAYER_OH_MY_JESUS, count: 1 },
            ]
        },
        { 
            steps: [
                { type: Constants.PRAYER_JOYFUL_MYSTERY_SECOND, count: 1 },
                { type: Constants.PRAYER_OUR_FATHER, count: 1 },
                { type: Constants.PRAYER_HAIL_MARY, count: 10 },
                { type: Constants.PRAYER_GLORY, count: 1 },
                { type: Constants.PRAYER_OH_MY_JESUS, count: 1 },
            ]
        },
        { 
            steps: [
                { type: Constants.PRAYER_JOYFUL_MYSTERY_THIRD, count: 1 },
                { type: Constants.PRAYER_OUR_FATHER, count: 1 },
                { type: Constants.PRAYER_HAIL_MARY, count: 10 },
                { type: Constants.PRAYER_GLORY, count: 1 },
                { type: Constants.PRAYER_OH_MY_JESUS, count: 1 },
            ]
        },
        { 
            steps: [
                { type: Constants.PRAYER_JOYFUL_MYSTERY_FOURTH, count: 1 },
                { type: Constants.PRAYER_OUR_FATHER, count: 1 },
                { type: Constants.PRAYER_HAIL_MARY, count: 10 },
                { type: Constants.PRAYER_GLORY, count: 1 },
                { type: Constants.PRAYER_OH_MY_JESUS, count: 1 },
            ]
        },
        { 
            steps: [
                { type: Constants.PRAYER_JOYFUL_MYSTERY_FIFTH, count: 1 },
                { type: Constants.PRAYER_OUR_FATHER, count: 1 },
                { type: Constants.PRAYER_HAIL_MARY, count: 10 },
                { type: Constants.PRAYER_GLORY, count: 1 },
                { type: Constants.PRAYER_OH_MY_JESUS, count: 1 },
            ]
        },
        { 
            steps: [
                { type: Constants.PRAYER_INFINITE_GRACE, count: 1 },
                { type: Constants.PRAYER_HAIL_HOLY_QUEEN, count: 1 },
            ]
        },
    ]
}

