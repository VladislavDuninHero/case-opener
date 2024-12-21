export {BalanceListener};

class BalanceListener {
    duration = 13_500;
    url = "api/v1/game/balance";

    async putBalance() {
        setTimeout( async () => {
            let balance = document.querySelector(".p__balance");

            let response = await this.getBalance();

            balance.textContent = Number.parseFloat(response.balance).toFixed(1);
        }, this.duration)
    }

    async getBalance() {
        return await fetch(this.url, {
            method: "GET"
        })
            .then(res => res.json())
            .then(json => {
                return {
                    "balance": json.balance
                }
            });
    }
}