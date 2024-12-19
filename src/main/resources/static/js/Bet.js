import {Carousel} from "./Carousel.js";

document.addEventListener("DOMContentLoaded", () => {
    const bet = new Bet();
    const carousel = new Carousel();

    document.querySelector(".main__bet-button")
        .addEventListener("click", async () => {
            const response = await bet.submitBet(1);

            carousel.runCarousel(response.multiplier);
        });
})

class Bet {

    url = "game/api/v1/bet";
    submitButton = document.querySelector(".main__bet-button");

    async submitBet(amount) {
        const data = {
            "amount": amount
        }
        this.submitButton.disabled = true;

        return await fetch(this.url, {
            method: "POST",
            headers: {
                "Content-type": "application/json"
            },
            body: JSON.stringify(data)
        })
            .then(res => res.json())
            .then(json => {
                return {
                    "multiplier": json.multiplier,
                    "payout": json.payout
                }
            });
    }
}