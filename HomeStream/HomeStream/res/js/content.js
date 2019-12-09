var elementWidth = parseInt(screen.width / 6);
const cover = document.getElementsByName("cover");
const coverText = document.getElementsByName("coverText");
const coverPlayer = document.getElementsByName("coverPlayer");

console.log(elementWidth);

for (var e of cover)
{
    e.width = elementWidth;
    console.log(e.width);
}

for (var e of coverText)
{
    e.style.width = elementWidth + "px";
    console.log(e.style.width);
}

for (var e of coverPlayer)
{
    e.style.width = elementWidth + "px";
    e.style.top = (elementWidth - 39) + "px";
    console.log(e.style.width);
    console.log(e.style.top);
}