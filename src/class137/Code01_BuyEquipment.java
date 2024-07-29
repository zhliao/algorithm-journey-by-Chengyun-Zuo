package class137;

// 装备购买
// 一共有n个物品，每个物品都有m个属性值
// 如果有k个物品，每个物品给一个相乘的系数，然后把属性值相加
// 可以得到第k+1个物品，那么就说第k+1个物品是没必要的，比如
// a = { 4, 6, 2 }, b = { 2, 8, 4 }, c = { 6, 19, 9 }
// a * 0.5 + b * 2 = c，所以c物品是不必要的
// 每个物品除了属性值之外，还有价格，现在希望尽量多的购买物品
// 如果已经选择了一些物品，而下一件物品是不必要的，那么不能购买
// 返回最少的花费
// 1 <= n、m <= 500
// 0 <= 属性值 <= 1000
// 测试链接 : https://www.luogu.com.cn/problem/P3265
// 提交以下的code，提交时请把类名改成"Main"，可以通过所有测试用例

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.util.Arrays;

public class Code01_BuyEquipment {

	public static int MAXN = 502;

	public static int MAXM = 502;

	public static double sml = 1e-5;

	public static double[][] mat = new double[MAXN][MAXM];

	// 记录的是编号不是状态
	public static int[] basis = new int[MAXN];

	public static int n, m;

	public static int cnt, ans;

	public static void compute() {
		cnt = ans = 0;
		Arrays.sort(mat, 1, n + 1, (a, b) -> a[m + 1] <= b[m + 1] ? -1 : 1);
		for (int i = 1; i <= n; i++) {
			if (insert(i)) {
				cnt++;
				ans += (int) mat[i][m + 1];
			}
		}
	}

	public static boolean insert(int i) {
		for (int j = 1; j <= m; j++) {
			if (Math.abs(mat[i][j]) >= sml) {
				if (basis[j] == 0) {
					basis[j] = i;
					return true;
				}
				double rate = mat[i][j] / mat[basis[j]][j];
				for (int k = j; k <= m; k++) {
					mat[i][k] -= rate * mat[basis[j]][k];
				}
			}
		}
		return false;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StreamTokenizer in = new StreamTokenizer(br);
		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
		in.nextToken();
		n = (int) in.nval;
		in.nextToken();
		m = (int) in.nval;
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= m; j++) {
				in.nextToken();
				mat[i][j] = (double) in.nval;
			}
		}
		for (int i = 1; i <= n; i++) {
			in.nextToken();
			mat[i][m + 1] = (double) in.nval;
		}
		compute();
		out.println(cnt + " " + ans);
		out.flush();
		out.close();
		br.close();
	}

}