class Carousel {
    currentIndex = 0;
    slideInterval;
    slideDuration = 1000;
    stopDuration = 13_000;
    slides = document.querySelectorAll(".carousel__img");
    submitButton = document.querySelector(".main__bet-button");
    totalSlides = this.slides.length;
    winnerMap = new Map([
            [0.2, "0.2x.png"],
            [0.5, "0.5x.png"],
            [0, "0x.png"],
            [1, "1x.png"],
            [2, "2x.png"],
            [5, "5x.png"],
            [10, "10x.png"],
            [20, "20x.png"],
            [40, "40x.png"],
            [60, "60x.png"],
            [80, "80x.png"],
            [120, "120x.png"]
        ]
    );
    winnerKey;

    runCarousel(multiplier) {
        console.log(this.winnerMap.get(multiplier));
        this.winnerKey = multiplier;

        this.currentIndex = Math.floor(Math.random() * this.totalSlides);

        this.slides.forEach(slide => slide.classList.remove("active"));

        this.slideInterval = setInterval(() => {

            this.slides[this.currentIndex].classList.remove("active");
            this.currentIndex = (this.currentIndex + 1) % this.totalSlides;
            this.slides[this.currentIndex].classList.add("active");

        }, this.slideDuration)

        setTimeout(() => {
            this.stopCarousel();
        }, this.stopDuration);
    }

    stopCarousel() {
        clearInterval(this.slideInterval);

        setTimeout(() => {
            this.showWinner();
            this.submitButton.disabled = false;
        }, 500);
    }

    showWinner() {
        this.slides.forEach(slide => slide.classList.remove("active"));

        const winningSlide = Array.from(this.slides).find(slide => slide.src.includes(this.winnerMap.get(this.winnerKey)));
        if (winningSlide) {
            winningSlide.classList.add("active");
        }
    }
}

export {Carousel};