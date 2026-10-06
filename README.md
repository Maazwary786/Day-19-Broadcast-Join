# Day 19 - Broadcast Join

A small branch reference table is broadcast to executors.

Broadcast join avoids shuffling the large transaction DataFrame when the reference table is sufficiently small.

Scenario:
Millions of transactions + small branch master.
