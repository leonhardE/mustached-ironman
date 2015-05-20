// BOOKS
var BookModel = Backbone.Model.extend({});
var BookCollection = Backbone.Collection.extend({
	model: BookModel
});

var brandNewBook = new BookModel({
	title: '1984',
	author: 'George Orwel'
});

function bbRead() {
	var books = new BookCollection();
	books.url = '/webapi/books';
	books.fetch({
		success: function(books, res, opt) {
			books.each(function(book) {
				console.log(book.get('author') + ": " + book.get('title'));
			});
		}
	});
	console.log(books);
}

function bbSave() {
	brandNewBook.save();
}
