// window.onscroll = function() {scrollFunction()};

// function scrollFunction() {
//   if (document.body.scrollTop > 20 || document.documentElement.scrollTop > 20) {
//     document.getElementById("navbar").style.top = "0";
//   } else {
//     document.getElementById("navbar").style.top = "-50px";
//   }
// }

document.querySelectorAll('.chapter button').forEach(function (e) {
  e.addEventListener('click', function () {
    window.location = "./chapter.html";
});
});

document.querySelectorAll('.carousel-item').forEach(function (e) {
  e.addEventListener('click', function () {
    window.location = "./chapter.html";
});
});

document.querySelectorAll('.comment .old-comments img').forEach(function (e) {
  console.log("account");
  e.addEventListener('click', function () {
    window.location = "./includes/account-info.html";
});
});