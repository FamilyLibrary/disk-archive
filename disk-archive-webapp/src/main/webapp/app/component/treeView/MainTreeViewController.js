Ext.define('app.component.treeView.MainTreeViewController', {
    extend: 'Ext.app.ViewController',
    alias: 'controller.treeView.MainTreeViewController',

    onItemClick: function(oThis, record, item, index, e, eOpts) {
        var treeView = this.getView();
        var panel = treeView.up('panel');
        var content = panel.lookupReference('content');

        if (content.items.length > 0) {
            content.removeAll(false);
        }
        var grid;
        if (record.data.text == 'Book Categories') {
            grid = Ext.create('app.component.categories.BookCategoriesGrid');
        } else if (record.data.text == 'Books') {
            grid = Ext.create('app.component.books.BooksGrid');
        }
        if (grid) {
            content.add(grid);
        }
    }
});