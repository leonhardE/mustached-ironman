var BookModel = Backbone.Model.extend({
	urlRoot: '/webapi/books'
});
var BookCollection = Backbone.Collection.extend({
	model: BookModel,
	url: '/webapi/books'
});

var brandNewBook = new BookModel({
	title: '1984',
	author: 'George Orwel'
});

function bbSave() {
	brandNewBook.save();
}
