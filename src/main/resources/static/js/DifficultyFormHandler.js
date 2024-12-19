window.addEventListener("DOMContentLoaded", (e) => {
    const handler = new DifficultyFormHandler();

    document.querySelector(".game-difficulty-form")
        .addEventListener("submit", e => {
            e.preventDefault();

            const response = handler.submitFormInitiate();

            response.then(res => {
                window.location.href = res.url;
            })
        });
});

class DifficultyFormHandler {

    submitFormInitiate() {
        const difficulty = document.querySelector(".game-difficulty-form__select");
        const form = document.querySelector(".game-difficulty-form");

        form.addEventListener("submit", (e) => e.preventDefault());

        const gameDTO = {
            gameName: "caseOpener",
            difficulty: difficulty.value
        };

        return this.submitForm(gameDTO);
    }

    async submitForm(data) {
        return await fetch("/game", {
            method: "POST",
            headers: {
                "Content-type": "application/json"
            },
            body: JSON.stringify(data)
        });
    }

}
