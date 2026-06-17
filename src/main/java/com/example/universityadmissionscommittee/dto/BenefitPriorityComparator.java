package com.example.universityadmissionscommittee.dto;

import java.util.Comparator;
import java.util.List;

public class BenefitPriorityComparator
        implements Comparator<List<BenefitIdNamePoints>> {

    public static final BenefitPriorityComparator INSTANCE =
            new BenefitPriorityComparator();

    @Override
    public int compare(
            List<BenefitIdNamePoints> left,
            List<BenefitIdNamePoints> right) {

        List<Integer> leftPoints = left.stream()
                .map(BenefitIdNamePoints::points)
                .sorted(Comparator.reverseOrder())
                .toList();

        List<Integer> rightPoints = right.stream()
                .map(BenefitIdNamePoints::points)
                .sorted(Comparator.reverseOrder())
                .toList();

        int max = Math.max(leftPoints.size(), rightPoints.size());

        for (int i = 0; i < max; i++) {

            Integer l = i < leftPoints.size()
                    ? leftPoints.get(i)
                    : null;

            Integer r = i < rightPoints.size()
                    ? rightPoints.get(i)
                    : null;

            if (l == null && r == null) {
                return 0;
            }

            if (l == null) {
                return 1;
            }

            if (r == null) {
                return -1;
            }

            int cmp = Integer.compare(r, l);

            if (cmp != 0) {
                return cmp;
            }
        }

        return 0;
    }
}
