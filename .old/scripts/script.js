var nav = document.getElementById("navMenu");
if (typeof(nav) != 'undefined' && nav != null){
  document.getElementById("navMenu").innerHTML =
    '<a href="index.html">home</a>' +
    '<a href="portfolio.html">portfolio</a>' +
    '<a href="cv.html">ciriculum vitae</a>' +
    //'<a href="blog.html">blog</a>' +
    '<a href="contact.html">contact</a>';
}

var blogNav = document.getElementById("blogNavMenu");
if (typeof(blogNav) != 'undefined' && blogNav != null){
  document.getElementById("blogNavMenu").innerHTML =
    '<a href="../index.html">home</a>' +
    '<a href="../portfolio.html">portfolio</a>' +
    '<a href="../cv.html">ciriculum vitae</a>' +
    //'<a href="../blog.html">blog</a>' +
    '<a href="../contact.html">contact</a>';
}

var uni = document.getElementById('uniWorkMenu');
if (typeof(uni) != 'undefined' && uni != null) {
  document.getElementById("uniWorkMenu").innerHTML =
    '<p>YEAR 1</p>'+
    '<a href="car.html">computer architecture</a>' +
    '<a href="indadd.html">introduction to database design and development</a>' +
    '<a href="intprog.html">introduction to programming</a>' +
    '<a href="netfun.html">network fundamentals</a>' +
    '<a href="webf1.html">web foundations 1</a>'+
    '<p>YEAR 2</p>'+
    '<a href="3dcgaa.html">3d computer graphics and animation</a>'+
    '<a href="adproc.html">advanced programming concepts</a>'+
    '<a href="inse.html">introduction to software engineering</a>'+
    '<a href="dsalg.html">data structures and algorithms</a>'+
    '<a href="cosine.html">computer operating systems and intermediate networking</a>'+
    '<a href="mathfun.html">discrete mathematics and functional programming</a>';
}

var pers = document.getElementById('persWorkMenu');
if (typeof(pers) != 'undefined' && pers != null) {
  document.getElementById("persWorkMenu").innerHTML =
    //'<a href="project-showcase.html">project showcase</a>' +
    //'<a href="3d-modelling-log.html">3D modelling log</a>' +
    '<a href="unity-log.html">unity log</a>';
}
