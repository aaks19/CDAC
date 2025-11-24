class ProductService {
  constructor() {
    this.prodarr = [
      { pid: 1, pname: "chair", qty: 34, price: 4589, mfgdate: "2025-11-11" },
      { pid: 2, pname: "table", qty: 50, price: 8000, mfgdate: "2025-10-11" },
      { pid: 3, pname: "shelf", qty: 67, price: 2345, mfgdate: "2024-10-11" },
      { pid: 4, pname: "stool", qty: 55, price: 2589, mfgdate: "2012-09-11" },
    ];
  }

  getAllProducts() {
    return this.prodarr;
  }
  addProduct(product) {
    this.prodarr.push(product);
  }

  editProduct(updatedProduct) {
    this.prodarr = this.prodarr.map((prod) =>
      prod.pid === updatedProduct.pid ? updatedProduct : prod
    );
  }

  deleteProduct(pid) {
    this.prodarr = this.prodarr.filter((prod) => prod.pid !== pid);
  }

  viewProduct(pid) {
    return this.prodarr.find((prod) => prod.pid === pid);
  }
}

export default new ProductService();
