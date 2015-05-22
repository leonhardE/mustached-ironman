var browserify = require('browserify');
var gulp = require('gulp');
var source = require('vinyl-source-stream');
var reactify = require('reactify');
var buffer = require('vinyl-buffer');
var uglify = require('gulp-uglify');
var debug = require('gulp-debug');

gulp.task('browserify', function () {
    return browserify('src/main/javascript/app/index.jsx', {
        debug: true,
        transform: [reactify]
    })
        .bundle()
        .pipe(source('bundle.js'))
        // .pipe(buffer())
        // .pipe(uglify())
        .pipe(gulp.dest('src/main/webapp/resources/js'));
});

gulp.task('html', function () {
    return gulp.src('src/main/javascript/assets/**/*.html')
    	.pipe(debug({title: 'html-pipe:'}))
        .pipe(gulp.dest('src/main/webapp'));
});

gulp.task('build', function () {
    gulp.start(['browserify', 'html']);
});

gulp.task('watch', ['build'], function () {
    gulp.watch(['src/main/javascript/app/**/*.jsx', 'src/main/javascript/app/**/*.js'], ['browserify']);
    gulp.watch(['src/main/javascript/assets/**/*.html'], ['html']);
});

