import {Carousel} from "./Carousel.js";
import {BalanceListener} from "./BalanceListener.js";
import {ExceptionHandler} from "./ExceptionHandler.js";

document.addEventListener("DOMContentLoaded", () => {
    const exceptionField = document.querySelector(".article__exception");
    const exceptionHandler = new ExceptionHandler(exceptionField);
    const bet = new Bet(exceptionHandler);
    const carousel = new Carousel();
    const balanceListener = new BalanceListener();

    const defaultActive = document.querySelector(".carousel__img");
    defaultActive.classList.add("active");

    document.querySelector(".main__bet-button")
        .addEventListener("click", async () => {

            const response = await bet.submitBet(1);
            if(bet.betError) {
                return;
            }

            exceptionField.textContent = "";

            carousel.runCarousel(response.multiplier);

            await balanceListener.putBalance();
        });
})

class Bet {
    constructor(exceptionHandler) {
        this.exceptionHandler = exceptionHandler;
    }

    url = "game/api/v1/bet";
    submitButton = document.querySelector(".main__bet-button");
    betError = false;

    async submitBet(amount) {
        const data = {
            "amount": amount
        }
        this.submitButton.disabled = true;

        try {
            const response = await fetch(this.url, {
                method: "POST",
                headers: {
                    "Content-type": "application/json"
                },
                body: JSON.stringify(data)
            });

            if (!response.ok) {
                const json = await response.json();
                const errors = json.errors.map((el) => {
                    return {
                        code: el.errorCode,
                        message: el.message
                    }
                });
                this.betError = true;

                throw new AggregateError(errors);
            }

            const json = await response.json();
            return {
                "multiplier": json.multiplier,
                "payout": json.payout
            }

        } catch (error) {
            error.errors.forEach(error => {
                this.exceptionHandler.handle(error.code, error.message);
            })
            this.submitButton.disabled = false;
        }
    }
}