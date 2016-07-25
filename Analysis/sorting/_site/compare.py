from random import randint
from random import shuffle

import matplotlib.pyplot as plt
plt.figure()
from multiprocessing import Process, Pipe
import time
import random

def radixSort(a,n,maxLen):
    for x in range(maxLen):
        bins = [[] for i in range(n)]
        for y in a:
            bins[(y/10**x)%n].append(y)
        a=[]
        for section in bins:
            a.extend(section)
    return a
    gap = (gap * 10) / 13
    if gap == 9 or gap == 10:
        gap = 11
    return max(1, gap)

def combsort(x):
    gap = len(x)
    swapped = True
    if gap < 2:
        return
    while gap > 1 or swapped:
        gap = update_gap(gap)
        swapped = False
        for i in range(0, len(x) - gap, gap):
            if x[i] > x[i + gap]:
                x[i], x[i + gap] = x[i + gap], x[i]
                swapped = True
            if x[i] == x[i + gap]:
                swapped = True
 
def shellSort(alist):
    sublistcount = len(alist)//2
    while sublistcount > 0:

      for startposition in range(sublistcount):
        gapInsertionSort(alist,startposition,sublistcount)

      print("After increments of size",sublistcount,
                                   "The list is",alist)

      sublistcount = sublistcount // 2

def gapInsertionSort(alist,start,gap):
    for i in range(start+gap,len(alist),gap):

        currentvalue = alist[i]
        position = i

        while position>=gap and alist[position-gap]>currentvalue:
            alist[position]=alist[position-gap]
            position = position-gap

        alist[position]=currentvalue


def cocktailSort(A):
    up = range(len(A)-1)
    while True:
        for indices in (up, reversed(up)):
            swapped = False
            for i in indices:
                if A[i] > A[i+1]:  
                    A[i], A[i+1] =  A[i+1], A[i]
                    swapped = True
            if not swapped:
                return




def heapsort( aList ):
  # convert aList to heap
  length = len( aList ) - 1
  leastParent = length / 2
  for i in range ( leastParent, -1, -1 ):
    moveDown( aList, i, length )
 
  # flatten heap into sorted array
  for i in range ( length, 0, -1 ):
    if aList[0] > aList[i]:
      swap( aList, 0, i )
      moveDown( aList, 0, i - 1 )






def moveDown( aList, first, last ):
  largest = 2 * first + 1
  while largest <= last:
    # right child exists and is larger than left child
    if ( largest < last ) and ( aList[largest] < aList[largest + 1] ):
      largest += 1
 
    # right child is larger than parent
    if aList[largest] > aList[first]:
      swap( aList, largest, first )
      # move down to largest child
      first = largest;
      largest = 2 * first + 1
    else:
      return # force exit
 
 








def quicksort( aList ):
    _quicksort( aList, 0, len( aList ) - 1 )
 
def _quicksort( aList, first, last ):
    if first < last:
      pivot = partition( aList, first, last )
      _quicksort( aList, first, pivot - 1 )
      _quicksort( aList, pivot + 1, last )
 
 
def partition( aList, first, last ) :
    pivot = first + random.randrange( last - first + 1 )
    swap( aList, pivot, last )
    for i in range( first, last ):
      if aList[i] <= aList[last]:
        swap( aList, i, first )
        first += 1
 
    swap( aList, first, last )
    return first
 
 
def swap( A, x, y ):
  A[x],A[y]=A[y],A[x]



def insertionsort( aList ):
  for i in range( 1, len( aList ) ):
    tmp = aList[i]
    k = i
    while k > 0 and tmp < aList[k - 1]:
        aList[k] = aList[k - 1]
        k -= 1
    aList[k] = tmp

def mergesort( aList ):
  _mergesort( aList, 0, len( aList ) - 1 )
 
 
def _mergesort( aList, first, last ):
  # break problem into smaller structurally identical pieces
  mid = ( first + last ) / 2
  if first < last:
    _mergesort( aList, first, mid )
    _mergesort( aList, mid + 1, last )
 
  # merge solved pieces to get solution to original problem
  a, f, l = 0, first, mid + 1
  tmp = [None] * ( last - first + 1 )
 
  while f <= mid and l <= last:
    if aList[f] < aList[l] :
      tmp[a] = aList[f]
      f += 1
    else:
      tmp[a] = aList[l]
      l += 1
    a += 1
 
  if f <= mid :
    tmp[a:] = aList[f:mid + 1]
 
  if l <= last:
    tmp[a:] = aList[l:last + 1]
 
  a = 0
  while first <= last:
    aList[first] = tmp[a]
    first += 1
    a += 1

tmpList=[]
y1=[]
y2=[]
y3=[]
y4=[]
y5=[]
y6=[]
y7=[]
x_series=range(1,6000)

for i in x_series:
	
	tmpList[len(tmpList):]=[randint(0,10000000)]
	time1=time.time()
	sorted(tmpList)
	time2=time.time()
	shuffle(tmpList)
	time3=time.time()
	mergesort(tmpList)
	time4=time.time()
	shuffle(tmpList)
	time5=time.time()
	quicksort(tmpList)
	time6=time.time()
	shuffle(tmpList)
	time7=time.time()
	heapsort(tmpList)
	time8=time.time()
	shuffle(tmpList)

	time9=time.time()
#	cocktailSort(tmpList)
	time10=time.time()
	shuffle(tmpList)
	time11=time.time()
#	shellSort(tmpList)
	time12=time.time()
#	shuffle(tmpList)
	time13=time.time()
	radixSort(tmpList,10,5)
	time14=time.time()
	y1+=[(time2-time1)]
	y2+=[(time4-time3)]
	y3+=[(time6-time5)]
	y4+=[(time8-time7)]
	y5+=[(time10-time9)]
	y6+=[(time12-time11)]
	y7+=[(time14-time13)]
	print "n="+str(i)+" insertionSort:"+str((time2-time1))+" mergeSort:"+str((time4-time3))

plt.plot(x_series, y1,label="timsort")
plt.plot(x_series, y2,label="mergeSort")
plt.plot(x_series, y3,label="quicksort")
plt.plot(x_series, y4,label="heapsort")
#plt.plot(x_series, y6,label="shellsort")
plt.plot(x_series, y7,label="Radix sort LSD")
plt.legend(loc="upper left")
plt.savefig("example.png")
