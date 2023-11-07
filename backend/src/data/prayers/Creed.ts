import { Prayer, trimAll } from "@/data/prayers/Prayer"
import { Constants } from "@/data/Constants";

export class Creed_PT implements Prayer {
    type: string = Constants.TYPE_CREED;
    title: string = "Creio";  
    paragraphs: string[] = trimAll(
        [
            `Creio em Deus Pai Todo-poderoso,
            Criador do Céu e da Terra
            e em Jesus Cristo, seu único filho, nosso Senhor
            que foi concebido pelo poder do Espírito Santo;
            nasceu da Virgem Maria;
            padeceu sob Pôncio Pilatos,
            foi crucificado, morto e sepultado;
            desceu à mansão dos mortos;
            ressuscitou ao terceiro dia;
            subiu aos céus;
            está sentado à direita de Deus Pai Todo-poderoso,
            de onde há de vir a julgar os vivos e os mortos.`,
            
            `Creio no Espírito Santo;
            na santa Igreja Católica;
            na comunhão dos Santos;
            na remissão dos pecados;
            na ressurreição da carne;
            e na vida eterna.`,
            
            `Amém`,
        ]
    )
}

export class Creed_EN implements Prayer {
    type: string = Constants.TYPE_CREED;
    title: string = "Creed";
    paragraphs: string[] = trimAll(
        [
            `I believe in God, the Father almighty, 
            Creator of heaven and earth. 
            I believe in Jesus Christ, God's only Son, our Lord, 
            who was conceived by the Holy Spirit, 
            born of the Virgin Mary, 
            suffered under Pontius Pilate, 
            was crucified, died, and was buried;
            he descended to the dead.
            On the third day he rose again; 
            he ascended into heaven, 
            he is seated at the right hand of the Father, 
            and he will come again to judge the living and the dead.`,

            `I believe in the Holy Spirit, 
            the holy catholic church, 
            the communion of saints, 
            the forgiveness of sins, 
            the resurrection of the body, 
            and the life everlasting.`,
        
            `Amen.`
        ]
    )
}