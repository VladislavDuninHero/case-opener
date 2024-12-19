class Carousel {
    currentIndex = 0;
    slideInterval;
    slideDuration = 1000;
    stopDuration = 13_000;
    slides = document.querySelectorAll(".carousel__img");
    carousel = document.querySelector(".carousel");
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


    }
}

export {Carousel};