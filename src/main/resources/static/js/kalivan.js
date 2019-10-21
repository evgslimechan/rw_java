const startTime = new Date().getTime();
const waveLetters = "КаВоТа ПаТиРяЛ СлаДкИй?";
const waveTarget = document.getElementsByClassName('kalivan')[0];
for (let i = 0; i < waveLetters.length; i++) {
    $(`<span class="letter">${ waveLetters[i] }</span>`).appendTo(waveTarget);
}
function runWave() {
    const width = $(window).width();
    const letterSpacing = width > 900 ? 50 : 30;
    const elapsed = new Date().getTime() - startTime;
    const pos = elapsed * 0.05;
    $('.kalivan .letter').each(function(index, letter) {
        const posx = width - (pos - letterSpacing * index) % width;
        const posy = Math.sin(posx / 40) * 20;
        $(letter).css('left', posx + 'px');
        $(letter).css('top', (posy+200) + 'px');
    });
}
setInterval(runWave, 1);