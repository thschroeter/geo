package geo

// http://www-graphics.stanford.edu/~seander/bithacks.html#InterleaveBMN

object Morton {

  /**
   * Only correct for unsigned integer, assuming this is just
   * used for visualizations.
   */
  // TODO: range check or use unsigned
  def morton(a: Int, b: Int): Int = m0(a) | (m0(b) << 1)

  def m0(a: Int): Int = {
    val b = Vector(0x55555555, 0x33333333, 0x0F0F0F0F, 0x00FF00FF)
    val s = Vector(1, 2, 4, 8)
    var x = a
    x = (x | (x << s(3))) & b(3)
    x = (x | (x << s(2))) & b(2)
    x = (x | (x << s(1))) & b(1)
    x = (x | (x << s(0))) & b(0)
    x
  }
}