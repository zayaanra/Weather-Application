package structure

import app.Weather

class SinglyLinkedList[Weather] extends scala.collection.mutable.Seq[Weather] {

  class Node[A](var value: A, var next: Node[A])

  private var headNode: Node[Weather] = null
  private var tailNode: Node[Weather] = null
  private var elems: Int = 0

  def insert(idx: Int, elem: Weather): Unit = {
    /* If idx is 0 and the length is 0, then we insert our first node */
    var cur = headNode
    if (idx == 0 && elems == 0) {
      headNode = new Node[Weather](elem, null)
      tailNode = headNode
      elems += 1
    }
    /* If idx is 0 and the list is non-empty, we insert a new head */
    else if (idx == 0 && elems != 0) { headNode = new Node[Weather](elem, headNode) }
    /* If idx is at the tail node, we insert a new tail */
    else if (idx == elems - 1) {
      tailNode.next = new Node[Weather](elem, null)
      elems += 1
    }
    /* If the idx is in between the head and tail, we must traverse the linked list */
    else {
      for (_ <- 0 until idx - 1) cur = cur.next
      cur.next = new Node[Weather](elem, cur.next)
    }
  }

  //TODO: Add functionality to remove a node

  override def update(idx: Int, elem: Weather): Unit = {
    assert(0 <= idx && idx <= length)
    var cur = headNode
    for (i <- 0 to idx) cur = cur.next
    cur.value = elem
  }

  override def length: Int = elems

  override def apply(idx: Int): Weather = {
    assert(0 <= idx && idx <= length)
    var cur = headNode
    for (i <- 0 to idx) cur = cur.next
    cur.value
  }

  override def iterator: Iterator[Weather] = new Iterator[Weather] {
    var cur: Node[Weather] = headNode

    override def hasNext: Boolean = cur != null

    override def next(): Weather = {
      val node = cur.value
      cur = cur.next
      node
    }
  }
}
